create table F18_08_Customer
 (Customer_ID INT not null,
  Name VARCHAR(35),
  date_of_birth DATE,
  gender VARCHAR(1) check (gender in ('m', 'f')),
  phone VARCHAR(15),
  city VARCHAR(20),
  CONSTRAINT customer_pk PRIMARY KEY (Customer_ID)); 


create table F18_08_Employee
 (Employee_ID INT not null,
  Name VARCHAR(35),
  date_of_birth DATE,
  gender VARCHAR(1) check (gender in ('m', 'f')),
  phone VARCHAR(15),
  city VARCHAR(20),
  designation VARCHAR(20),
  salary INT,
  CONSTRAINT employee_pk PRIMARY KEY (Employee_ID));


create table F18_08_Store
 (Store_ID INT NOT NULL,
  phone VARCHAR(15),
  Emp_ID INT NOT NULL,
  CONSTRAINT storepk PRIMARY KEY(Store_ID),
  FOREIGN KEY(Emp_ID) REFERENCES F18_08_Employee(Employee_ID) ON DELETE SET NULL);


create table F18_08_Car
 (Car_ID INT NOT NULL,
  CarType VARCHAR(20) check (CarType in ('SUV', 'Sedan', 'Compact Sedan', 'Pickup truck', 'Hatchbacks', 'Premium')),
  CarModel VARCHAR(20),
  Transmission VARCHAR(1) check (Transmission in ('a', 'm')),
  Production_year INT,
  Store_ID INT not null,
  CONSTRAINT carpk PRIMARY KEY (Car_ID),
  FOREIGN KEY(Store_ID) REFERENCES F18_08_Store(Store_ID) ON DELETE SET NULL);


create table F18_08_Accessory
 (Accessory_ID INT NOT NULL,
  Price NUMBER(2),
  Type VARCHAR(35) check (Type in ('Child safety seats', 'roof rack', 'GPS', 'ropes')),
  CONSTRAINT accpk PRIMARY KEY(Accessory_ID));


create table F18_08_AssignedTo
 (Car_ID INT NOT NULL,
  Accessory_ID INT NOT NULL,
  PRIMARY KEY (Car_ID, Accessory_ID),
  FOREIGN KEY(Car_ID) REFERENCES F18_08_Car(Car_ID) ON DELETE SET NULL,
  FOREIGN KEY(Accessory_ID) REFERENCES F18_08_Accessory(Accessory_ID) ON DELETE SET NULL);  


create table F18_08_Rents
 (Rental_Start_Date DATE not null,
  Rental_End_date DATE,
  Odometer_End_Read NUMBER(3),
  Odometer_Start_Read NUMBER(3),
  RentalCost_per_day NUMBER(2),
  CarPickupCity VARCHAR(20),
  CarDropCity VARCHAR(20),
  Customer_ID INT not null,
  Car_ID INT not null,
  Store_ID INT not null,
  PRIMARY KEY(Rental_Start_Date, Customer_ID, Car_ID, Store_ID),
  FOREIGN KEY(Customer_ID) REFERENCES F18_08_Customer(Customer_ID) ON DELETE SET NULL,
  FOREIGN KEY(Car_ID) REFERENCES F18_08_Car(Car_ID) ON DELETE SET NULL, 
  FOREIGN KEY(Store_ID) REFERENCES F18_08_Store(Store_ID) ON DELETE SET NULL);


create table F18_08_Pays
 (date_of_payment DATE not null,
  mode_of_payment VARCHAR(4) check (mode_of_payment in ('cash', 'card')),
  totalRentalCost NUMBER(2),
  Customer_ID INT not null,
  Store_ID INT not null,
  Car_ID INT not null,
  PRIMARY KEY(date_of_payment, Customer_ID, Car_ID, Store_ID),
  FOREIGN KEY(Customer_ID) REFERENCES F18_08_Customer(Customer_ID) ON DELETE SET NULL,
  FOREIGN KEY(Car_ID) REFERENCES F18_08_Car(Car_ID) ON DELETE SET NULL, 
  FOREIGN KEY(Store_ID) REFERENCES F18_08_Store(Store_ID) ON DELETE SET NULL);
