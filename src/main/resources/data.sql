INSERT
INTO
  branch
  (id, name)
VALUES
  ('B1', 'Vashi');


INSERT
INTO
  branch_vehicle_mapping
  (id, branch_id, vehicle_Type)
VALUES
  (1, 'B1', 'VAN'),(2, 'B1', 'BUS'),(3, 'B1', 'CAR');


INSERT
INTO
  vehicle
  (id, branch_id, type, price)
VALUES
  ('V1', 'B1', 'VAN', 500);


  INSERT
INTO
  vehicle_booking_slot
  (id, vehicle_id, start_Time, end_Time)
VALUES
  (1, 'V1', '12:00', '13:00');