-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 03-Abr-2023 às 20:20
-- Projeto criado e executado por Diego Antonio
-- Versão do servidor: 5.5.21
-- versão do PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bancofarmacia`
--

CREATE DATABASE bancofarmacia;
USE bancofarmacia;

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpfCnpj` varchar(12) DEFAULT NULL,
  `endereco` varchar(50) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nome`, `cpfCnpj`, `endereco`, `telefone`) VALUES
(1, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(2, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(3, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(4, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(5, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(6, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(7, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(8, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(9, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(10, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(11, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(12, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(13, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999'),
(14, 'João Silva', '12345678901', 'Rua A, 123', '(11) 99999-9999');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens_venda`
--

DROP TABLE IF EXISTS `itens_venda`;
CREATE TABLE IF NOT EXISTS `itens_venda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `venda_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produto_id` (`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE IF NOT EXISTS `produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome`, `descricao`, `preco_unitario`, `quantidade_estoque`) VALUES
(1, 'Caneta', 'Caneta esferográfica preta', '2.50', 30),
(2, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(3, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(4, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(5, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(6, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(7, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(8, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(9, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(10, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(11, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(12, 'Caneta', 'Caneta esferográfica preta', '2.50', 100),
(13, 'Caneta', 'Caneta esferográfica preta', '2.50', 100);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipo` enum('admin','vendedor') NOT NULL DEFAULT 'vendedor',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`, `tipo`) VALUES
(1, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(2, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(3, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(4, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(5, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(6, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(7, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(8, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(9, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(10, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(11, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(12, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor'),
(13, 'Fulano de Tal', 'fulano@example.com', '123456', 'vendedor');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

DROP TABLE IF EXISTS `vendas`;
CREATE TABLE IF NOT EXISTS `vendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) NOT NULL,
  `produto_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  `data_venda` datetime NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`id`, `cliente_id`, `produto_id`, `usuario_id`, `data_venda`, `quantidade`, `valor_total`) VALUES
(1, 1, 1, 1, '2023-04-03 16:50:28', 10, '25.00'),
(2, 1, 1, 1, '2023-04-03 16:50:45', 10, '25.00'),
(3, 1, 1, 1, '2023-04-03 16:50:57', 10, '25.00'),
(4, 1, 1, 1, '2023-04-03 16:51:08', 10, '25.00'),
(5, 1, 1, 1, '2023-04-03 16:51:23', 10, '25.00'),
(6, 1, 1, 1, '2023-04-03 16:51:33', 10, '25.00'),
(7, 1, 1, 1, '2023-04-03 16:51:48', 10, '25.00'),
(8, 1, 1, 1, '2023-04-03 16:56:57', 10, '25.00');

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `itens_venda`
--
ALTER TABLE `itens_venda`
  ADD CONSTRAINT `itens_venda_ibfk_1` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
