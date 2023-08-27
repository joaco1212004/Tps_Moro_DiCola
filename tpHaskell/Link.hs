module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
where

import City 
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin

connectsL :: City -> Link -> Bool -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 _) | city1 == city = True 
                                   | city2 == city = True 
                                   | otherwise = False

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas están conectadas mediante este link
{-- Un Link es naturalmente bidireccional, Si las ciudades A y B están enlazadas por un link li, linksL A B li y linksL B A li es true --}
linksL city1 city2 (Lin c1 c2 _) | city1 == c1 && city2 == c2 = True 
                                 | city2 == c1 && city1 == c2 = True
                                 | otherwise = False 

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality 

delayL :: Link -> Float -- la demora que sufre una conexión en este canal
delayL (Lin city1 city2 quality) = delayQ quality * distanceC city1 city2

{-- esta demora es en unidades de tiempo --}
{-- La demora de un link es en tiempo, segundos, milisegundos, etc.
La demora de la calidad de un enlace es en velocidad, por ejemplo km/segundo.
No importan las unidades, sí la relación entre los valores --}
