CREATE DATABASE [JobApplication]
go
Use [JobApplication]

CREATE TABLE [dbo].[Account] (
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](32) NULL,
	[Password] [nvarchar](32) NULL,
	[isAdmin] [bit] NULL,
PRIMARY KEY CLUSTERED (
	[UserID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE TABLE [dbo].[Category] (
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](30),
	[LogoCategory] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED (
	[CategoryID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Job] (
  [JobID] [int] IDENTITY(1,1) NOT NULL,
  [UserID] [int] NULL,
  [JobName] [nvarchar](60) NULL,
  [Company] [nvarchar](60) NULL,
  [LogoCompany] [nvarchar](1000) NULL,
  [CategoryID] [int] NULL,
  [Type] [nvarchar](10) NULL,
  [Salary] [BIGINT] NULL,
  [Description] [nvarchar](MAX) NULL,
  [JD] [nvarchar](MAX) NULL,
  [Location] [nvarchar](200) NULL,
  [PublicDate] [date] NULL,
  [ClosingDate] [date] NULL,
  [Phone] [nvarchar](50) NULL,
  [Email] [nvarchar](50) NULL,
  CONSTRAINT fk_product_1 FOREIGN KEY ([CategoryID]) REFERENCES [dbo].[Category]([CategoryID]),
PRIMARY KEY CLUSTERED 
(
	[JobID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (1, N'Finance', N'<i class="lni-home"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (2, N'Sale & Marketing', N'<i class="lni-world"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (3, N'Education & Training', N'<i class="lni-book"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (4, N'Technologies', N'<i class="lni-display"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (5, N'Art & Design', N'<i class="lni-brush"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (6, N'Healthcare', N'<i class="lni-heart"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (7, N'Science', N'<i class="lni-funnel"></i>')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [LogoCategory]) VALUES (8, N'Food Services', N'<i class="lni-cup"></i>')
SET IDENTITY_INSERT [dbo].[Category] OFF

SET IDENTITY_INSERT [dbo].[Account] ON 
INSERT [dbo].[Account] ([UserID], [Username], [Password], [isAdmin]) VALUES (6, N'user123', N'123456', 1)
INSERT [dbo].[Account] ([UserID], [Username], [Password], [isAdmin]) VALUES (7, N'admin123', N'admin123', 1)
SET IDENTITY_INSERT [dbo].[Account] OFF