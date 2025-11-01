INSERT INTO patient (name,gender,email,birthdate,blood_group)
VALUES
    ('abhik','Male','abhiK@gmail.com','1995-03-05','O_Positive'),
    ('rahul"','Male','rahul@gmail.com','2005-07-05','O_Negative'),
    ('teja','Male','teja@gmail.com','2004-04-05','A_Positive'),
    ('vish','Male','vish@gmail.com','1998-01-05','B_Positive'),
    ('Tarini','Female','Tarini@gmail.com','1999-05-02','B_Positive');


INSERT INTO doctor (name, specialization, email)
VALUES
    ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
    ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
    ('Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
  ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
  ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
  ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
  ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
  ('2025-07-05 16:15:00', 'Consultation', 1, 4),
  ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);