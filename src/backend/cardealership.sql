-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2020 at 03:05 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cardealership`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_surname` varchar(20) DEFAULT NULL,
  `user_password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `user_name`, `user_surname`, `user_password`) VALUES
(1, 'Jon', 'Kojakovic', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5'),
(2, 'Dzejla', 'Suman', 'f09b0ea1d0336583a3bcf7418e1a4dac6fe8219235ca716fe2b7ed491c5d1956'),
(3, 'Vega', 'Haklicka', 'd9e4182d27600c9157e257c724efc3418b46ec7002c6bb72210739c06c23fcbc');

-- --------------------------------------------------------

--
-- Table structure for table `buyerinfo`
--

CREATE TABLE `buyerinfo` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `fullname` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `phonenumber` varchar(12) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buyerinfo`
--

INSERT INTO `buyerinfo` (`id`, `username`, `password`, `fullname`, `email`, `phonenumber`, `type`) VALUES
(1, 'admin', '', 'adminson', 'itsme@gmail.com', '095111111', 2);

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `car_id` int(11) NOT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `car_year` int(11) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `additional` varchar(20) DEFAULT NULL,
  `sold` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`car_id`, `brand`, `model`, `car_year`, `color`, `additional`, `sold`) VALUES
(1, 'Ford', 'Fiesta', 2014, 'cyan', 'aux', 0),
-- (2, 'Ford', 'Ka/Figo', 1996, 'red', 'seat warmer', 0),
(3, 'Ford', 'Focus', 2018, 'dark grey', 'aux', 0),
(4, 'Ford', 'Mondeo', 2015, 'dark red', 'aux', 0),
(5, 'Ford', 'Mustang', 2014, 'red', 'radio', 0),
(6, 'Ford', 'GT', 2004, 'dark blue', 'convertable', 1),
(7, 'Ford', 'Ranger Raptor', 2018, 'blue', 'trailer', 0),
(8, 'Ford', 'Focus ST', 2014, 'orange', 'aux', 0),
(9, 'Ford', 'Fiesta ST', 2014, 'red', 'aux', 0),
(10, 'Ford', 'F-150 Raptor', 2016, 'blue', 'trailer', 0),
(11, 'Fiat', '500', 2007, 'white', 'aux', 0),
(12, 'Fiat', 'Punto', 2012, 'red', 'aux', 0),
(13, 'Fiat', 'Bravo', 2007, 'grey', 'none', 0),
(14, 'Fiat', 'Doblo', 2010, 'dark red', 'none', 0),
(15, 'Fiat', 'Idea', 2010, 'grey', 'none', 0),
(16, 'Fiat', 'Bravo', 2014, 'black', 'radio', 0),
-- (17, 'Fiat', 'Marea', 1996, 'black', 'aux', 0),
-- (18, 'Fiat', 'Uno', 1983, 'blue', 'none', 0),
(19, 'Fiat', 'Tipo', 2016, 'grey', 'none', 0),
-- (20, 'Fiat', 'Argeta', 1982, 'pink', 'aux', 0),
(21, 'Renault', 'Clio IV', 2019, 'orange', 'aux', 0),
(22, 'Renault', 'Twingo', 2015, 'white', 'aux', 0),
(23, 'Renault', 'Talisman', 2016, 'grey', 'aux', 0),
(24, 'Renault', 'Captur', 2013, 'orange', 'aux', 0),
(25, 'Renault', 'Espace', 2020, 'bourdough', 'aux', 0),
(26, 'Renault', 'Megane Grand Scenic', 2020, 'beige', 'aux', 0),
(27, 'Renault', 'Megane Kadjar', 2019, 'blue', 'aux', 0),
(28, 'Renault', 'Megane', 2020, 'beige', 'aux', 0),
(29, 'Renault', 'Trafic', 2019, 'dark grey', '12 seats', 0),
(30, 'Renault', 'Koleos', 2008, 'brown', 'aux', 0),
(31, 'Ferrari', '458 Italia', 2009, 'Red', 'aux', 0);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `car_description` varchar(20) DEFAULT NULL,
  `car_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `car_id` int(11) DEFAULT NULL,
  `date_of_purchase` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`receipt_id`, `buyer_id`, `car_id`, `date_of_purchase`) VALUES
(6, 1, 9, '2020-12-01'),
(7, 1, 2, '2020-01-12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `buyerinfo`
--
ALTER TABLE `buyerinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`car_id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD KEY `car_id` (`car_id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receipt_id`),
  ADD KEY `car_id` (`car_id`),
  ADD KEY `buyer_id` (`buyer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `buyerinfo`
--
ALTER TABLE `buyerinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `receipt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`) ON DELETE CASCADE;

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `buyerinfo` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
