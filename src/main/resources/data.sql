INSERT
INTO
  branch
  (id)
VALUES
  ('B1');


INSERT
INTO
  branch_vehicle_mapping
  (id, branch_id, vehicle_Type)
VALUES
  (1000000, 'B1', 'VAN'),(2000000, 'B1', 'BUS'),(3000000, 'B1', 'CAR');


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
  (1000000, 'V1', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('17:00', 'HH:mm'));