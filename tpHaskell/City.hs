module City ( City, newC, nameC, distanceC )
where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC name point = Cit name point

nameC :: City -> String
nameC ( Cit name _ ) = name

distanceC :: City -> City -> Float
distanceC ( Cit _ point1 ) ( Cit _ point2 ) = difP point1 point2

test = [
        newC "Hola" (newP 2 3) == Cit "Hola" (newP 2 3),
        newC "Chau" (newP 4 5) == Cit "Chau" (newP 4 5),
        nameC (Cit "Hola" (newP 2 3)) == "Hola",
        distanceC (Cit "Hola" (newP 2 3)) (Cit "Chau" (newP 4 5)) == sqrt 8,
        True ]