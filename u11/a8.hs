from :: Int -> [Int]
from x = x : from (x+1)

drop_mult :: Int -> [Int] -> [Int]
drop_mult x xs = filter (\y -> mod y x /= 0) xs

dropall :: [Int] -> [Int]
dropall (x : xs) = x : dropall (drop_mult x xs)

primes :: [Int]
primes = dropall (from 2)

firstElement :: [Int] -> Int
firstElement [] = 0
firstElement (x : xs) = x

nextElement :: Int -> [Int] -> Int
nextElement _ [x] = 0
nextElement i (x : xs) =  if (x == i) then firstElement xs else nextElement i xs

previousElement :: Int -> [Int] -> Int
previousElement _ [x] = 0
previousElement i (x : xs) =  if x == i then 0 else (if ((firstElement xs) == i) then x else previousElement i xs)

-- Ist zwar logisch, funktioniert aber nicht!
-- primes, nextElement und previousElement tun was sie sollen
-- Problem ist also irgendwie der Filter! - Der gibt zu viel aus
goodPrimes :: [Int]
goodPrimes = filter (\x -> x * x > (previousElement x primes) * (nextElement x primes)) primes