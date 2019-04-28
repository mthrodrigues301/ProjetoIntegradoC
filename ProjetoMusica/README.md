<b>Comandos importantes</b>

<!------SCRIPT BANCO------->

USE [poo1913] GO

SET ANSI_NULLS ON GO

SET QUOTED_IDENTIFIER ON GO

CREATE TABLE [poo1913].[Musica]( 
[Id] [int] IDENTITY(1,1) NOT NULL,
[Titulo] varchar NOT NULL,
[Cantor] varchar NOT NULL,
[Estilo] varchar NOT NULL,
[Duracao] [int] NULL,
[Preco] [decimal](5, 2) NULL,
[Ativo] [bit] NOT NULL
) ON [PRIMARY] GO

INSERT INTO [poo1913].[Musica] VALUES ('Voce Vai Ver','Zeze Di Camargo e Luciano','Sertanejo',(RAND(123456)*(20-10+1))+10,ROUND(RAND(CHECKSUM(NEWID())) * (100), 2),1) GO
