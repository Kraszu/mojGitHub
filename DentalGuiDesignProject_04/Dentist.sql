-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2018 at 12:43 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dentist`
--

-- --------------------------------------------------------

--
-- Table structure for table `dentist`
--

CREATE TABLE `dentist` (
  `Username` varchar(15) NOT NULL,
  `Password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dentist`
--

INSERT INTO `dentist` (`Username`, `Password`) VALUES
('username', 'password');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `InvoiceNo` int(11) NOT NULL,
  `PatientID` int(11) NOT NULL,
  `OriginalAmount` double NOT NULL,
  `RemainingBalance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceNo`, `PatientID`, `OriginalAmount`, `RemainingBalance`) VALUES
(1, 2, 71, 50),
(2, 1, 1113.5, 0),
(3, 5, 106.5, 0),
(4, 2, 2000, 840),
(5, 0, 35.5, 35.5),
(6, 5, 35.5, 35.5),
(7, 1, 1000, 500),
(8, 5, 54, 54),
(9, 5, 1000, 1000),
(10, 3, 35.5, 35.5);

-- --------------------------------------------------------

--
-- Table structure for table `invoiceprocedure`
--

CREATE TABLE `invoiceprocedure` (
  `ID` int(11) NOT NULL,
  `InvoiceNo` int(11) NOT NULL,
  `ProcedureNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoiceprocedure`
--

INSERT INTO `invoiceprocedure` (`ID`, `InvoiceNo`, `ProcedureNo`) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 2, 1),
(4, 2, 2),
(5, 2, 3),
(6, 3, 1),
(7, 3, 1),
(8, 3, 1),
(9, 4, 3),
(10, 4, 3),
(11, 5, 5),


-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `ID` int(11) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `PhoneNumber` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`ID`, `fName`, `lName`, `Address`, `PhoneNumber`) VALUES

(1, 'Jack', 'Corcoran', 'Dublin', '086'),
(2, 'John ', 'Doe', 'Londyn', '044'),
(3, 'John', 'Lennon', 'Liverpol', '546545'),
(4, 'Maciej', 'Kubiniec', 'Cork', '0870643447'),
(5, 'Mark', 'MIllard', 'Bristol', '044051');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `ID` int(11) NOT NULL,
  `InvoiceNo` int(11) NOT NULL,
  `AmountPaid` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`ID`, `InvoiceNo`, `AmountPaid`) VALUES
(1, 3, 100),
(2, 1, 15),
(3, 4, 1150),
(4, 2, 1113.5),
(5, 4, 10),
(6, 1, 6),
(7, 3, 6.5),
(8, 7, 500);

-- --------------------------------------------------------

--
-- Table structure for table `procedure`
--

CREATE TABLE `procedure` (
  `ProcNo` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(300) NOT NULL,
  `Cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `procedure`
--

INSERT INTO `procedure` (`ProcNo`, `Name`, `Description`, `Cost`) VALUES
(1, 't_white', 'tooth whitening', 35.5),
(2, 'r_can', 'root canal', 78),
(3, 'br', 'braces', 1000),
(5, 'dfs', 'fsdfdf', 54),
(8, 'bor', 'borowanie', 150);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dentist`
--
ALTER TABLE `dentist`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`InvoiceNo`);

--
-- Indexes for table `invoiceprocedure`
--
ALTER TABLE `invoiceprocedure`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `procedure`
--
ALTER TABLE `procedure`
  ADD PRIMARY KEY (`ProcNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoiceprocedure`
--
ALTER TABLE `invoiceprocedure`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `procedure`
--
ALTER TABLE `procedure`
  MODIFY `ProcNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
