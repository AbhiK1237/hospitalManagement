package com.SpringLearn.example.hospitalManagement.security;

import com.SpringLearn.example.hospitalManagement.Enum.AuthProviderType;
import com.SpringLearn.example.hospitalManagement.dto.LoginRequestDto;
import com.SpringLearn.example.hospitalManagement.dto.LoginResponseDto;
import com.SpringLearn.example.hospitalManagement.dto.SignupResponseDto;
import com.SpringLearn.example.hospitalManagement.entitiy.User;
import com.SpringLearn.example.hospitalManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token,user.getId());
    }


    public User signUpInternal(LoginRequestDto signupRequestDto,AuthProviderType providerType,String providerId){
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if(user!=null)throw new IllegalArgumentException("user already Exists");
      user = User.builder()
              .username(signupRequestDto.getUsername())
              .providerType(providerType)
              .providerId(providerId)
              .build();

      if(providerType == AuthProviderType.EMAIL){
          user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

      }
        return userRepository.save(user);
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto,AuthProviderType.EMAIL,null);
        return new SignupResponseDto(user.getId(),user.getUsername());
    }


    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        AuthProviderType providerType = authUtil.getAuthProviderFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null){
            //signup flow
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId,providerId);
            user = signUpInternal(new LoginRequestDto(username,null),providerType,providerId);
        }else if(user!=null){
            if(email !=null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }else{
            throw new BadCredentialsException("This email is already registered with provider"+emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user),user.getId());
        return ResponseEntity.ok(loginResponseDto);
        //fetch providerType and provider Id
        //save providerType and provider Id info with user
        //if user has already an account: login
        //otherwise, first signup and then login
    }
}
