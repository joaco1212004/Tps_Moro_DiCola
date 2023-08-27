module Tunel ( Tunel, newT, connectsT, usesT, delayT )
where
{--Un Tunel es la conexión lógica que podemos establecer entre dos puntos, también es bidireccional --}

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)


newT :: [Link] -> Tunel
{-- El newT recibe una lista de links ordenada y válida, donde los extremos son los extremos del túnel. (Que sea válida significa que no hace falta revisarla) --}
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- indica si éste túnel conecta estas dos ciudades distintas
{-- dadas dos ciudades esta función da si si las ciudades son los extremos del túnel --}
connectsT city1 city2 (Tun tunel) | connectsL city1 (head tunel) && connectsL city2 (last tunel) = True
                                  | connectsL city2 (head tunel) && connectsL city1 (last tunel) = True
                                  | otherwise = False

usesT :: Link -> Tunel -> Bool -- indica si este túnel atraviesa ese link
{-- Un túnel recorre una serie de uno o más links, esta función indica si el link consultado es parte de esa serie --}
usesT _ (Tun []) = False
usesT link (Tun (x : tunel)) | link == x = True
                             | otherwise = usesT link (Tun tunel)

delayT :: Tunel -> Float -- la demora que sufre una conexión en este túnel
{-- esta demora es en unidades de tiempo, cuanto demora la información en recorrer el túnel --}
delayT (Tun []) = 0.0
delayT (Tun (x : tunel)) = delayL x + delayT (Tun tunel)
