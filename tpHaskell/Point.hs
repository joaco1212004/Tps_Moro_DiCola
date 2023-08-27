module Point ( Point, newP, difP)
where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

difP :: Point -> Point -> Float -- distancia absoluta
difP (Poi x1 y1) (Poi x2 y2) = sqrt (fromIntegral ((x2 - x1)^2 + (y2 - y1)^2))

test = [newP 2 3 == Poi 2 3,
        newP 4 5 == Poi 4 5,
        difP (Poi 2 3) (Poi 4 5) == sqrt 8,
        True ]