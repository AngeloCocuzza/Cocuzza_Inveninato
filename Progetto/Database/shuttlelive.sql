-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 23, 2023 alle 20:48
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shuttlelive`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autisti`
--

CREATE TABLE `autisti` (
  `username` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `datanascita` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `corsa`
--

CREATE TABLE `corsa` (
  `ID` int(11) NOT NULL,
  `autista` varchar(256) NOT NULL,
  `utente` varchar(256) NOT NULL,
  `veicolo` varchar(256) NOT NULL,
  `citta_partenza` varchar(256) NOT NULL,
  `citta_destinazione` varchar(256) NOT NULL,
  `data_partenza` date NOT NULL,
  `indirizzo_partenza` varchar(256) NOT NULL,
  `indirizzo_destinazione` varchar(256) NOT NULL,
  `ora_partenza` time NOT NULL,
  `km_corsa` int(11) NOT NULL,
  `prezzo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `corsa_programmati`
--

CREATE TABLE `corsa_programmati` (
  `ID_viaggio` int(11) NOT NULL,
  `utente` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `disponibilita`
--

CREATE TABLE `disponibilita` (
  `autista` varchar(255) NOT NULL,
  `giorno_disponibilita` date NOT NULL,
  `ora_inizio` time NOT NULL,
  `ora_fine` time NOT NULL,
  `citta_partenza` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `patente`
--

CREATE TABLE `patente` (
  `codice` varchar(256) NOT NULL,
  `autista` varchar(256) NOT NULL,
  `data_conseguimento` date NOT NULL,
  `data_scadenza` date NOT NULL,
  `livello` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `recensioni_corsa`
--

CREATE TABLE `recensioni_corsa` (
  `voto` int(11) NOT NULL,
  `commento` varchar(255) NOT NULL,
  `corsa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `recensioni_viaggi`
--

CREATE TABLE `recensioni_viaggi` (
  `voto` int(11) NOT NULL,
  `commento` varchar(255) NOT NULL,
  `viaggio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `username` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `datanascita` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `veicolo`
--

CREATE TABLE `veicolo` (
  `targa` varchar(256) NOT NULL,
  `autista` varchar(256) NOT NULL,
  `marca` varchar(256) NOT NULL,
  `modello` varchar(256) NOT NULL,
  `colore` varchar(256) NOT NULL,
  `n_posti` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `viaggi_programmati`
--

CREATE TABLE `viaggi_programmati` (
  `ID` int(11) NOT NULL,
  `autista` varchar(255) NOT NULL,
  `veicolo` varchar(255) NOT NULL,
  `citta_partenza` varchar(255) NOT NULL,
  `citta_destinazione` varchar(255) NOT NULL,
  `data_partenza` date NOT NULL,
  `indirizzo_partenza` varchar(255) NOT NULL,
  `indirizzo_destinazione` varchar(255) NOT NULL,
  `ora_partenza` time NOT NULL,
  `evento` varchar(255) NOT NULL,
  `km_corsa` int(11) NOT NULL,
  `prezzo` float NOT NULL,
  `n_posti_disp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `corsa`
--
ALTER TABLE `corsa`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `viaggi_programmati`
--
ALTER TABLE `viaggi_programmati`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `corsa`
--
ALTER TABLE `corsa`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `viaggi_programmati`
--
ALTER TABLE `viaggi_programmati`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
