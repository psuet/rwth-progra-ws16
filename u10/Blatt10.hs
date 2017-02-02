-- Teilaufgabe a)
isEven :: Int -> Bool
isEven 0 = True
isEven 1 = False
isEven x = isEven (x-2)
--1,5/1,5
-- Teilaufgabe b)
arithSeries :: Int -> Int -> Int
arithSeries x d = if (x <= d) then x else x + arithSeries (x-d) d
--1,5/1,5 
-- Teilaufgabe c)
isSorted :: [Int] -> Bool
isSorted (x : y : xs) = (x <= y) && isSorted (y : xs)
isSorted _ = True
--2/2 
-- Teilaufgabe d)
interval :: Int -> Int -> [Int]
interval a b = if b >= a then [a] ++ (interval (a + 1) b) else []
--1,5/1,5 
-- Teilaufgabe e)
sortListH :: [Int] -> [Int]
sortListH (x:y:xs)
    | x > y = y : sortListH (x:xs)
    | otherwise = x : sortListH (y:xs)
sortListH (x) = (x)

sortLista :: [Int] -> Int -> [Int]
sortLista xs i 
    | i == (length xs) = xs
    | otherwise = sortLista (sortListH xs) (i + 1) 
 
sortList :: [Int] -> [Int]
sortList xs = sortLista xs 0

selectKsmallest :: Int -> [Int] -> Int
selectKsmallest k [] = 0
selectKsmallest k xs = selectKsmallestH (sortList xs) (k-1)

selectKsmallestH :: [Int] -> Int -> Int
selectKsmallestH (x : xs) k = if (k<=0) then x else selectKsmallestH xs (k-1)
--5,5/5,5 
--12/12