--Made by Benjamin Stutte and not Paul Suetterlin
--Aufgabe 2

--a)
data MulTree a 	= IndexKnoten a a [MulTree a]
                | DatenKnoten a
                deriving Show


t1 :: MulTree Int
t1 = IndexKnoten 3 42 [IndexKnoten 3 15 [DatenKnoten 3, DatenKnoten 11, DatenKnoten 12],
                        IndexKnoten 19 42 [DatenKnoten 42, DatenKnoten 23]
                    ]
--b)
verzweigunggsgrad :: MulTree a -> Int
verzweigunggsgrad (DatenKnoten _) = 0
verzweigunggsgrad (IndexKnoten _ _ xs) = maxInteger (countList xs : makeList xs)
        where
            makeList :: [MulTree a] -> [Int]
            makeList [] = []
            makeList (x:xs) = countList xs : verzweigunggsgrad x : makeList xs

            countList :: [MulTree a] -> Int
            countList []  = 0
            countList (x:xs) = 1 + countList xs

            maxInteger :: [Int] -> Int
            maxInteger [] = 0
            maxInteger (x:xs) = if x > (maxInteger xs) then x else maxInteger xs

--c)
datenListe :: MulTree a -> [a]
datenListe (DatenKnoten a) = a:[]
datenListe (IndexKnoten a b xs) = a:b:iterateList xs
        where
            iterateList :: [MulTree a] -> [a]
            iterateList [] = []
            iterateList (x:xs) = datenListe x ++ iterateList xs

{--d)
datenIntervalle :: MulTree Int -> MulTree Int
datenIntervalle (DatenKnoten a) =
datenIntervalle (IndexKnoten a b as) =-} 

--e)
contains :: Int -> MulTree Int -> Bool
contains x (DatenKnoten a) = if a == x then True else False
contains x (IndexKnoten a b as)	| a > x || x > b = False
                                | a == x || b == x = True
                                | otherwise containshelp x as
                                where
                                    containshelp :: Int -> [MulTree Int] -> Bool
                                    containshelp x [] = False
                                    containshelp x (a:as) = contains x a

{-contains :: Int -> MulTree Int -> Bool
contains x t = treecontains x (datenListe t) 
	where
		treecontains :: Int -> [Int] -> Bool
		treecontains a [] = False
		treecontains a (x:xs) = if a == x then True else treecontains a xs