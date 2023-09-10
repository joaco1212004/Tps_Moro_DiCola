import Point
import City
import Quality
import Link
import Tunel
import Region

point1 = newP 2 3
point2 = newP 4 5
point3 = newP 1 1
point4 = newP 2 2
point5 = newP 3 3

city1 = newC "city1" point1
city2 = newC "city2" point2
city3 = newC "city3" point3
city4 = newC "city4" point4
city5 = newC "city5" point5

quality1 = newQ "quality1" 2 2.4
quality2 = newQ "quality2" 1 1.1
quality3 = newQ "quality3" 2 2.2

link_1_A_2 = newL city1 city2 quality1
link_2_A_3 = newL city2 city3 quality2
link_3_A_4 = newL city3 city4 quality3

tunnel_1_A_4 = newT [link_1_A_2, link_2_A_3, link_3_A_4]

region = newR
region1 = foundR region city1
region2 = foundR region1 city2
region3 = foundR region2 city3
region4 = foundR region3 city4
region5 = foundR region4 city5
region6 = linkR region5 city1 city2 quality1
region7 = linkR region6 city2 city3 quality2
region8 = linkR region7 city3 city4 quality3
region9 = tunelR region8 [city1, city2, city3, city4]

testPoint = [
            difP (newP 2 3) (newP 4 5) == sqrt 8,
            True 
            ]

testCity = [
            nameC city1 == "city1",
            distanceC city1 city2 == sqrt 8,
            True 
            ]

testQuality = [
                capacityQ quality1 == 2,
                delayQ quality1 == 2.4,
                True 
                ]

testLink = [
            connectsL city1 link_1_A_2, 
            linksL city2 city3 link_2_A_3,
            capacityL link_1_A_2 == 2, 
            delayL link_1_A_2 == sqrt 8 * 2.4,
            True
            ]

testTunel = [
            connectsT city1 city4 tunnel_1_A_4, 
            usesT link_1_A_2 tunnel_1_A_4, 
            delayT tunnel_1_A_4 == delayL link_1_A_2 + delayL link_2_A_3 + delayL link_3_A_4,
            True
            ] 

testRegion = [region /= region1,
              region2 /= region3,
              region4 /= region5,
              connectedR region9 city1 city4,
              linkedR region9 city1 city2,
              delayR region9 city1 city4 == delayL link_1_A_2 + delayL link_2_A_3 + delayL link_3_A_4,
              availableCapacityForR region9 city1 city2 == 1,
              True
              ]
