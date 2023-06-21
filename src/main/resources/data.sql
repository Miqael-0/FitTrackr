insert into goal(target_goal, start_date, end_date) values
    ('Weight Loss', '2023-01-01', '2023-06-30');
insert into goal(target_goal, start_date, end_date) values
    ('Muscle Gain', '2023-02-01', '2023-07-31');
insert into goal(target_goal, start_date, end_date) values
    ('Fitness Improvement', '2023-03-01', '2023-08-31');

insert into trainee(first_name, last_name, gender, age, height) values
    ('John', 'Doe', 'Male', 30, 180);
insert into trainee(first_name, last_name, gender, age, height) values
    ('Jane', 'Smith', 'Female', 25, 165);
insert into trainee(first_name, last_name, gender, age, height) values
    ('Mike', 'Johnson', 'Male', 35, 175);

insert into workout(workout_name) values
    ('Cardio');
insert into workout(workout_name) values
    ('Strength Training');
insert into workout(workout_name) values
    ('Yoga');
insert into workout(workout_name) values
    ('Pilates');

UPDATE trainee SET goal_id = 1
WHERE trainee_id = 1;

UPDATE trainee SET goal_id = 2
WHERE trainee_id = 2;

UPDATE trainee SET goal_id = 3
WHERE trainee_id = 3;
