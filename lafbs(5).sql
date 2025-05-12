-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 12 mai 2025 à 14:37
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lafbs`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonne`
--

CREATE TABLE `abonne` (
  `ABO_MATRICULE` int(11) NOT NULL,
  `ABO_NOM` varchar(25) DEFAULT NULL,
  `ABO_PRENOM` varchar(25) DEFAULT NULL,
  `ABO_DDN` datetime DEFAULT NULL,
  `ABO_TELEPHONE` varchar(12) DEFAULT NULL,
  `ABO_MAIL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Table des abonnes';

--
-- Déchargement des données de la table `abonne`
--

INSERT INTO `abonne` (`ABO_MATRICULE`, `ABO_NOM`, `ABO_PRENOM`, `ABO_DDN`, `ABO_TELEPHONE`, `ABO_MAIL`) VALUES
(2, 'Martin', 'Sophie', '1985-03-22 00:00:00', '0609876543', 'sophie.martin@example.com'),
(8, 'Dupont', 'Jean', '1990-01-15 00:00:00', '0601234567', 'jean.dupont@example.com');

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `AB_ABO_MATRICULE` int(11) NOT NULL,
  `AB_SAL_ID` varchar(10) NOT NULL,
  `AB_TAB_CODE` varchar(5) NOT NULL,
  `AB_ID` int(11) NOT NULL,
  `AD_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Abonnement a une salle avec un type d''abonnement';

--
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`AB_ABO_MATRICULE`, `AB_SAL_ID`, `AB_TAB_CODE`, `AB_ID`, `AD_DATE`) VALUES
(2, 'S002', 'AC01', 2, '2025-05-03 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `abonnesalle`
--

CREATE TABLE `abonnesalle` (
  `SAB_ABO_MATRICULE` int(11) NOT NULL,
  `SAB_SAL_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `entrainement`
--

CREATE TABLE `entrainement` (
  `ENT_ABO_MATRICULE` int(11) NOT NULL,
  `ENT_SAL_ID` varchar(10) NOT NULL,
  `ENT_DATE` datetime DEFAULT NULL,
  `ENT_HEUREARRIVEE` datetime DEFAULT NULL,
  `ENT_HEUREDEPART` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Entrainement individuel dans la salle';

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `INS_ID` int(11) NOT NULL,
  `INS_SEA_ID` int(11) NOT NULL,
  `INS_ABO_MATRICULE` int(11) NOT NULL,
  `INS_DATE` date DEFAULT NULL,
  `INS_PRESENT` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Inscription d''un abonne Ultimate a une seance d''un club';

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`INS_ID`, `INS_SEA_ID`, `INS_ABO_MATRICULE`, `INS_DATE`, `INS_PRESENT`) VALUES
(2, 60, 2, '2025-05-02', 0);

-- --------------------------------------------------------

--
-- Structure de la table `prestation`
--

CREATE TABLE `prestation` (
  `PRE_CODE` varchar(10) NOT NULL,
  `PRE_LIBELLE` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Prestations lives au type d''abonnement';

--
-- Déchargement des données de la table `prestation`
--

INSERT INTO `prestation` (`PRE_CODE`, `PRE_LIBELLE`) VALUES
('P001', 'Accès illimités à tous les clubs'),
('P002', 'Home training avec Home Sport'),
('P003', 'Cours vidéo accès libre'),
('P004', 'Entraînement et suivi de la progression avec l\'application'),
('P005', 'Hydromassage'),
('P006', '-10% sur la boutique du club'),
('P007', 'Fight sport'),
('P008', 'Burning sport'),
('P009', 'Cycle sport'),
('P010', 'Yoga'),
('P011', 'Pilate'),
('P012', 'SH\'bam'),
('P013', 'Body pump'),
('P014', 'Cours collectifs avec coach');

-- --------------------------------------------------------

--
-- Structure de la table `prestationabonnement`
--

CREATE TABLE `prestationabonnement` (
  `APR_TAB_CODE` varchar(5) NOT NULL,
  `APR_PRE_CODE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `prestationabonnement`
--

INSERT INTO `prestationabonnement` (`APR_TAB_CODE`, `APR_PRE_CODE`) VALUES
('AC01', 'P001'),
('AC01', 'P002'),
('AC01', 'P003'),
('AC01', 'P004'),
('AC01', 'P005'),
('AC01', 'P011'),
('AC01', 'P012'),
('CL01', 'P001'),
('CL01', 'P002'),
('CL01', 'P003'),
('CL01', 'P004'),
('UL01', 'P001'),
('UL01', 'P002'),
('UL01', 'P003'),
('UL01', 'P004'),
('UL01', 'P005'),
('UL01', 'P006'),
('UL01', 'P007'),
('UL01', 'P008'),
('UL01', 'P009'),
('UL01', 'P010');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `SAL_ID` varchar(10) NOT NULL,
  `SAL_NOM` varchar(70) DEFAULT NULL,
  `SAL_ADRRUE` varchar(50) DEFAULT NULL,
  `SAL_ADVILLE` varchar(50) DEFAULT NULL,
  `SAL_TELEPHONE` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Salle de sport';

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`SAL_ID`, `SAL_NOM`, `SAL_ADRRUE`, `SAL_ADVILLE`, `SAL_TELEPHONE`) VALUES
('S001', 'Republique', '10 place de la République', 'Paris', '0145789632'),
('S002', 'Montmartre', '17 du Faubourg Monmartre', 'Paris', '0456789123'),
('S003', 'Lyon', '2 avenue de la Gare', 'Paris', '0498761234');

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

CREATE TABLE `seance` (
  `SEA_SAL_ID` varchar(10) NOT NULL,
  `SEA_PRE_CODE` varchar(10) NOT NULL,
  `SEA_JOURSEM` int(11) DEFAULT NULL,
  `SEA_HORAIRE` time DEFAULT NULL,
  `SEA_ID` int(11) NOT NULL,
  `SEA_NBPLACE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`SEA_SAL_ID`, `SEA_PRE_CODE`, `SEA_JOURSEM`, `SEA_HORAIRE`, `SEA_ID`, `SEA_NBPLACE`) VALUES
('S001', 'P010', 3, '18:30:00', 60, 30),
('S001', 'P005', 2, '15:18:00', 76, 25);

-- --------------------------------------------------------

--
-- Structure de la table `typeabonnement`
--

CREATE TABLE `typeabonnement` (
  `TAB_CODE` varchar(5) NOT NULL,
  `TAB_LIBELLE` varchar(50) DEFAULT NULL,
  `TAB_TARIF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Abonnement Classic, Access+, ultimate';

--
-- Déchargement des données de la table `typeabonnement`
--

INSERT INTO `typeabonnement` (`TAB_CODE`, `TAB_LIBELLE`, `TAB_TARIF`) VALUES
('AC01', 'Access+', 50),
('CL01', 'Classic', 30),
('UL01', 'Ultimate', 70);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `use_id` int(11) NOT NULL,
  `use_nom` varchar(255) NOT NULL,
  `use_pass` varchar(255) NOT NULL,
  `use_role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`use_id`, `use_nom`, `use_pass`, `use_role`) VALUES
(1, 'hadi', 'fKNaFux5DbwmZsye7DOHRK7fvQ7vuyRgKa9e/Asi5J8=:NJqAf+r6PrOTRMtIS4HRdlPHDzgYyY8iJS9R94+OylQ=', 'admin'),
(2, 'consult', 'AXqg46eajH8DSQ3pUyegyPdOOVS1o3Bhsl3Ie/JpQm8=:6FqdL3LLzUM8P0TuPxGEePGzagqxOVNMrcpRB7K1LP4=', 'consult'),
(3, 'user', 'KSVm2oE9o4mXOOe1Jze13ndpX7sd1Evxbyol6F/fRFs=:dpDWGN/WMnlBhDbwBH1zacD/HSHvhomHt0c86YhT6hI=', 'adherent');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonne`
--
ALTER TABLE `abonne`
  ADD PRIMARY KEY (`ABO_MATRICULE`);

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`AB_ID`),
  ADD KEY `I_FK_ABONNEMENT_ABONNE` (`AB_ABO_MATRICULE`),
  ADD KEY `I_FK_ABONNEMENT_SALLE` (`AB_SAL_ID`),
  ADD KEY `I_FK_ABONNEMENT_TYPEABONNEMENT` (`AB_TAB_CODE`);

--
-- Index pour la table `abonnesalle`
--
ALTER TABLE `abonnesalle`
  ADD PRIMARY KEY (`SAB_ABO_MATRICULE`,`SAB_SAL_ID`),
  ADD KEY `I_FK_ABONNESALLE_ABONNE` (`SAB_ABO_MATRICULE`),
  ADD KEY `I_FK_ABONNESALLE_SALLE` (`SAB_SAL_ID`);

--
-- Index pour la table `entrainement`
--
ALTER TABLE `entrainement`
  ADD PRIMARY KEY (`ENT_ABO_MATRICULE`,`ENT_SAL_ID`),
  ADD KEY `I_FK_ENTRAINEMENT_ABONNE` (`ENT_ABO_MATRICULE`),
  ADD KEY `I_FK_ENTRAINEMENT_SALLE` (`ENT_SAL_ID`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`INS_ID`),
  ADD KEY `I_FK_INSCRIPTION_SEANCE` (`INS_SEA_ID`),
  ADD KEY `I_FK_INSCRIPTION_ABONNE` (`INS_ABO_MATRICULE`);

--
-- Index pour la table `prestation`
--
ALTER TABLE `prestation`
  ADD PRIMARY KEY (`PRE_CODE`);

--
-- Index pour la table `prestationabonnement`
--
ALTER TABLE `prestationabonnement`
  ADD PRIMARY KEY (`APR_TAB_CODE`,`APR_PRE_CODE`),
  ADD KEY `I_FK_PRESTATIONABONNEMENT_TYPEABONNEMENT` (`APR_TAB_CODE`),
  ADD KEY `I_FK_PRESTATIONABONNEMENT_PRESTATION` (`APR_PRE_CODE`);

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`SAL_ID`);

--
-- Index pour la table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`SEA_ID`),
  ADD KEY `I_FK_SEANCE_SALLE` (`SEA_SAL_ID`),
  ADD KEY `I_FK_SEANCE_PRESTATION` (`SEA_PRE_CODE`);

--
-- Index pour la table `typeabonnement`
--
ALTER TABLE `typeabonnement`
  ADD PRIMARY KEY (`TAB_CODE`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`use_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `seance`
--
ALTER TABLE `seance`
  MODIFY `SEA_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `use_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `FK_ABONNEMENT_ABONNE` FOREIGN KEY (`AB_ABO_MATRICULE`) REFERENCES `abonne` (`ABO_MATRICULE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ABONNEMENT_SALLE` FOREIGN KEY (`AB_SAL_ID`) REFERENCES `salle` (`SAL_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ABONNEMENT_TYPEABONNEMENT` FOREIGN KEY (`AB_TAB_CODE`) REFERENCES `typeabonnement` (`TAB_CODE`) ON DELETE CASCADE;

--
-- Contraintes pour la table `abonnesalle`
--
ALTER TABLE `abonnesalle`
  ADD CONSTRAINT `FK_ABONNESALLE_ABONNE` FOREIGN KEY (`SAB_ABO_MATRICULE`) REFERENCES `abonne` (`ABO_MATRICULE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ABONNESALLE_SALLE` FOREIGN KEY (`SAB_SAL_ID`) REFERENCES `salle` (`SAL_ID`) ON DELETE CASCADE;

--
-- Contraintes pour la table `entrainement`
--
ALTER TABLE `entrainement`
  ADD CONSTRAINT `FK_ENTRAINEMENT_ABONNE` FOREIGN KEY (`ENT_ABO_MATRICULE`) REFERENCES `abonne` (`ABO_MATRICULE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ENTRAINEMENT_SALLE` FOREIGN KEY (`ENT_SAL_ID`) REFERENCES `salle` (`SAL_ID`) ON DELETE CASCADE;

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `FK_INSCRIPTION_ABONNE` FOREIGN KEY (`INS_ABO_MATRICULE`) REFERENCES `abonne` (`ABO_MATRICULE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_INSCRIPTION_SEANCE` FOREIGN KEY (`INS_SEA_ID`) REFERENCES `seance` (`SEA_ID`) ON DELETE CASCADE;

--
-- Contraintes pour la table `prestationabonnement`
--
ALTER TABLE `prestationabonnement`
  ADD CONSTRAINT `FK_PRESTATIONABONNEMENT_PRESTATION` FOREIGN KEY (`APR_PRE_CODE`) REFERENCES `prestation` (`PRE_CODE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_PRESTATIONABONNEMENT_TYPEABONNEMENT` FOREIGN KEY (`APR_TAB_CODE`) REFERENCES `typeabonnement` (`TAB_CODE`) ON DELETE CASCADE;

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `FK_SEANCE_PRESTATION` FOREIGN KEY (`SEA_PRE_CODE`) REFERENCES `prestation` (`PRE_CODE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_SEANCE_SALLE` FOREIGN KEY (`SEA_SAL_ID`) REFERENCES `salle` (`SAL_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
