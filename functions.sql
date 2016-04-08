DROP FUNCTION IF EXISTS available ;

DELIMITER |

CREATE FUNCTION available(book DECIMAL(13,0)) RETURNS DECIMAL(4,0)

BEGIN
  DECLARE ttl DECIMAL(4,0) ;
  DECLARE numout DECIMAL(4,0) ;

  SET ttl=(SELECT SUM(num_tot) FROM Assets WHERE book=ISBN) ;
  SET numout=(SELECT SUM(num_out) FROM CheckedOut WHERE book=ISBN) ;
  IF numout IS NULL THEN
    SET numout=0 ;
  END IF ;
  RETURN ttl-numout ;
END |

DROP PROCEDURE IF EXISTS checkout |

CREATE PROCEDURE checkout(patron DECIMAL(9,0), book DECIMAL(9,0), num DECIMAL(2,0))

BEGIN
  DECLARE numout DECIMAL(2,0) ;

  IF (SELECT * FROM Users WHERE uid=patron) IS NULL THEN
    SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT='No such patron' ;
  END IF ;
  SET numout=(SELECT num_out FROM CheckedOut WHERE book=ISBN AND patron=uid) ;
  IF available(book)<num THEN
    SIGNAL SQLSTATE '40001' SET MESSAGE_TEXT='Not enough assets available' ;
  ELSEIF numout IS NULL THEN
    INSERT INTO CheckedOut (uid, ISBN, num_out) VALUES (patron, book, num);
  ELSE
    UPDATE CheckedOut SET num_out=num_out+num WHERE book=ISBN AND patron=uid ;
  END IF ;
END |

DROP PROCEDURE IF EXISTS checkin |

CREATE PROCEDURE checkin(patron DECIMAL(9,0), book DECIMAL(13,0), num DECIMAL(2,0))

BEGIN
  DECLARE numout DECIMAL(2,0) ;

  SET numout=(SELECT num_out FROM CheckedOut WHERE book=ISBN AND patron=uid) ;
  IF numout IS NULL OR numout<num THEN
    SIGNAL SQLSTATE '40002' SET MESSAGE_TEXT='Patron does not have that many checked-out' ;
  ELSEIF numout=num THEN
    DELETE FROM CheckedOut WHERE book=ISBN AND patron=uid ;
  ELSE
    UPDATE CheckedOut SET num_out=num_out-num WHERE book=ISBN AND PATRON=uid;
  END IF ;
END |

DELIMITER ;
