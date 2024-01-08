-- Tạo cơ sở dữ liệu mới
CREATE DATABASE IF NOT EXISTS MangaSever;

-- Chọn cơ sở dữ liệu mới
USE MangaSever;

-- Bảng Author
CREATE TABLE Author (
    AuthorID INT PRIMARY KEY,
    AuthorName VARCHAR(255) NOT NULL
);

-- Bảng Manga
CREATE TABLE Manga (
    MangaID INT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description TEXT,
    AuthorID INT,
    ThumbnailImg VARCHAR(255),
    Status BOOLEAN,
    FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID)
);

-- Bảng Tag
CREATE TABLE Tag (
    TagID INT PRIMARY KEY,
    TagName VARCHAR(255) NOT NULL
);

-- Bảng MangaTag
CREATE TABLE MangaTag (
    MangaID INT,
    TagID INT,
    PRIMARY KEY (MangaID, TagID),
    FOREIGN KEY (MangaID) REFERENCES Manga(MangaID),
    FOREIGN KEY (TagID) REFERENCES Tag(TagID)
);

-- Bảng Chapter
CREATE TABLE Chapter (
    ChapterID INT PRIMARY KEY,
    MangaID INT,
    Name VARCHAR(255) NOT NULL,
    Date DATE,
    FOREIGN KEY (MangaID) REFERENCES Manga(MangaID)
);

-- Bảng Image
CREATE TABLE Image (
    ImageID INT PRIMARY KEY,
    ChapterID INT,
    ImagePath VARCHAR(255) NOT NULL,
    FOREIGN KEY (ChapterID) REFERENCES Chapter(ChapterID)
);
