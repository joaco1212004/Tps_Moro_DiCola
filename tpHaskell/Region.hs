module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
where

import City
import Link
import Quality
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Eq, Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
{-- Hay decisiones que tomar! --}
foundR (Reg cities links tunels ) city = Reg (city:cities) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
{-- Hay decisiones que tomar! --}
linkR (Reg cities links tunels) city1 city2 quality = Reg cities (newL city1 city2 quality : links) tunels

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
{-- la lista de ciudades indica el camino ordenado de enlaces que debe tomar el túnel de inicio a fin --}
{-- Hay decisiones que tomar! --}
tunelR (Reg cities links tunels) cities1 | not (all (`elem` cities) cities) = error "No estan todas la ciudades en la Region"
                                         | length links < length (cities1) - 1 = error "No hay links suficientes para generar la conexion"
                                         | not (linksTest (Reg cities links tunels) (citiesinR links cities1)) = error "La capacidad de un link fue superada"
                                         | otherwise = Reg cities links (newT (citiesinR links cities1):tunels)

linksTest :: Region ->  [Link] -> Bool
linksTest (Reg _ _ []) _ = True
linksTest (Reg _ _ tunels) links = [link | link <- links, capacityLinks link tunels < capacityL link] == links

citiesinR :: [Link] -> [City] -> [Link]
citiesinR links [] = []
citiesinR links [_] = []
citiesinR links (city1:city2:cities) = [link | link <- links, linksL city1 city2 link] ++ citiesinR links (city2:cities)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están conectadas por un túnel
{-- Dice si existe un túnel en la región que tenga estas ciudades por origen o destino --}
connectedR (Reg _ _ []) _ _ = False
connectedR (Reg cities links (x : tunels)) city1 city2 | connectsT city1 city2 x = True
                                                       | otherwise = connectedR (Reg cities links tunels) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están enlazadas
{-- Dice si existe un enlace en la región que entre estas dos ciudades --}
linkedR (Reg _ [] _) _ _ = False
linkedR (Reg cities (x : links) tunels) city1 city2 | linksL city1 city2 x = True
                                                    | otherwise = linkedR (Reg cities links tunels) city1 city2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
{-- Hay decisiones que tomar! --}
delayR (Reg _ [] _) _ _ = error "No existe la conexion en la region"
delayR (Reg cities links (x:tunels)) city1 city2    | connectsT city1 city2 x = delayT x
                                                    | otherwise = delayR (Reg cities links tunels) city1 city2


availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
{-- Teniendo en cuenta la capacidad que los túneles existentes ocupan --}
availableCapacityForR (Reg _ [] _) _ _ = error "No existe la conexion en la region"
availableCapacityForR (Reg cities (link:links) tunels) city1 city2  | linksL city1 city2 link = capacityL link - capacityLinks link tunels
                                                                    | otherwise = availableCapacityForR (Reg cities links tunels) city1 city2

capacityLinks :: Link -> [Tunel] -> Int
capacityLinks link tunels = length ([tunel | tunel <- tunels, usesT link tunel])

{-- La conexión sólo se da a través de un túnel, y sólo se conectan los extremos --}
{-- Cada vez que se refiere a 'conectadas', necesariamente se refiere a un túnel --}

