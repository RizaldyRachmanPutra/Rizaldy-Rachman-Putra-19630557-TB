-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2022 at 02:11 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rental`
--
CREATE DATABASE IF NOT EXISTS `rental` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `rental`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'rizal', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE IF NOT EXISTS `mobil` (
  `kode_mobil` varchar(15) NOT NULL,
  `nama_mobil` varchar(100) NOT NULL,
  `no_polisi` varchar(15) NOT NULL,
  `tahun_mobil` int(10) NOT NULL,
  `tarif_sewa` int(100) NOT NULL,
  PRIMARY KEY (`kode_mobil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`kode_mobil`, `nama_mobil`, `no_polisi`, `tahun_mobil`, `tarif_sewa`) VALUES
('M01', 'Toyota Avanza ', 'B 9231 A', 2015, 300000),
('M02', 'Toyota Innova', 'DA 2312 E', 2016, 350000),
('M03', 'Suzuki Ertiga', 'N 1979 IA', 2015, 300000),
('M04', 'Honda Jazz', 'L 7821 RR', 2017, 350000),
('M05', 'Honda CR-V', 'DA 3821 OI', 2017, 400000),
('M06', 'Daihatsu Rocky', 'B 2001 ER', 2020, 400000),
('M07', 'Toyota Raize', 'DA 1234 R', 2020, 400000);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE IF NOT EXISTS `pelanggan` (
  `kode_pelanggan` varchar(15) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(25) NOT NULL,
  `pekerjaan` varchar(50) NOT NULL,
  `no_telepon` varchar(15) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  PRIMARY KEY (`kode_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kode_pelanggan`, `nama`, `jenis_kelamin`, `pekerjaan`, `no_telepon`, `alamat`) VALUES
('P01', 'Rizaldy', 'Laki-Laki', 'PNS', '08957040', 'Sultan Adam'),
('P02', 'Erika', 'Perempuan', 'PNS', '08953414', 'Sungai Andai'),
('P03', 'Fajar', 'Laki-Laki', 'Polisi', '0851034', 'Mesjid Jami'),
('P04', 'Keysha', 'Perempuan', 'Bankir', '08125232', 'Bina Brata'),
('P05', 'Ayesha', 'Perempuan', 'Swasta', '08512321', 'Gatot Subroto'),
('P06', 'Yosida', 'Perempuan', 'PLN', '08134515', 'Kayu Tangi'),
('P07', 'Shella', 'Perempuan', 'Bidan', '087812', 'Cinda Alus');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `kode_transaksi` varchar(25) NOT NULL,
  `kode_pelanggan` varchar(25) NOT NULL,
  `kode_mobil` varchar(25) NOT NULL,
  `tarif_sewa` int(50) NOT NULL,
  `tgl_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `total_bayar` int(50) NOT NULL,
  PRIMARY KEY (`kode_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`kode_transaksi`, `kode_pelanggan`, `kode_mobil`, `tarif_sewa`, `tgl_sewa`, `tgl_kembali`, `total_bayar`) VALUES
('T01', 'P01', 'M01', 300000, '2021-12-02', '2021-12-03', 600000),
('T02', 'P02', 'M02', 350000, '2021-06-15', '2021-06-18', 1400000),
('T03', 'P03', 'M03', 300000, '2021-12-01', '2021-12-04', 1200000),
('T04', 'P04', 'M04', 350000, '2021-12-03', '2021-12-09', 2100000),
('T05', 'P05', 'M05', 400000, '2021-11-01', '2021-11-05', 1600000),
('T06', 'P06', 'M06', 400000, '2021-06-12', '2021-06-22', 4000000),
('T07', 'P07', 'M07', 400000, '2020-12-06', '2020-12-09', 1200000);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
