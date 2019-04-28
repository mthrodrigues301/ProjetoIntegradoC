<b>Comandos importantes</b></br>

USE [poo1913] GO</br>

SET ANSI_NULLS ON GO</br>

SET QUOTED_IDENTIFIER ON GO</br>
CREATE TABLE [poo1913].[Musica](</br> 
<b>[Id]</b> [int] IDENTITY(1,1) NOT NULL,</br>
<b>[Titulo]</b> varchar(80) NOT NULL,</br>
<b>[Cantor]</b> varchar(60) NOT NULL,</br>
<b>[Estilo]</b> varchar(30) NOT NULL,</br>
<b>[Duracao]</b> [int] NULL,</br>
<b>[Preco]</b> [decimal](5, 2) NULL,</br>
<b>[Ativo]</b> [bit] NOT NULL</br>
) ON [PRIMARY] GO
