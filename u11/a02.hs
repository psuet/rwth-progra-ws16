--a)
data MulTree a = IndexKnoten a a [MulTree a]
               | DatenKnoten a
               deriving Show


t1 :: MulTree Int
t1 = IndexKnoten 3 42 [IndexKnoten 3 15 [DatenKnoten 3, DatenKnoten 11, DatenKnoten 12],
                        IndexKnoten 19 42 [DatenKnoten 42, DatenKnoten 23]
                    ]
--b)
verzweigungsgrad :: MulTree a -> Int
verzweigungsgrad (DatenKnoten _) = 0
verzweigungsgrad (IndexKnoten _ _ xs) = maxInteger (countList xs : makeList xs)

makeList :: [MulTree a] -> [Int]
makeList [] = []
makeList (x:xs) = countList xs : verzweigungsgrad x : makeList xs

countList :: [MulTree a] -> Int
countList []  = 0
countList (x:xs) = 1 + countList xs

maxInteger :: [Int] -> Int
maxInteger [] = 0
maxInteger (x:xs) = if x > (maxInteger xs) then x else maxInteger xs

--c)
datenListe :: MulTree a -> [a]
datenListe (DatenKnoten a) = a:[]
datenListe (IndexKnoten a b xs) = iterateList xs

iterateList :: [MulTree a] -> [a]
iterateList [] = []
iterateList (x:xs) = datenListe x ++ iterateList xs

--d)
datenIntervalle :: MulTree Int -> MulTree Int
datenIntervalle (DatenKnoten a) = DatenKnoten a
datenIntervalle (IndexKnoten a b xs) = if a < b then IndexKnoten (minList (datenKnoten xs) maxBound) (maxList (datenKnoten xs) minBound) (datenIntervalle' xs) else IndexKnoten (maxList (datenKnoten xs) minBound) (minList (datenKnoten xs) maxBound) (datenIntervalle' xs)

datenIntervalle' :: [MulTree Int] -> [MulTree Int]
datenIntervalle' [] = []
datenIntervalle' (t : ts) = datenIntervalle t : datenIntervalle' ts

datenKnoten :: [MulTree a] -> [a]
datenKnoten [] = []
datenKnoten (t:ts) = datenListe t ++ datenKnoten ts

maxList :: [Int] -> Int -> Int
maxList [] i = i
maxList (x : xs) i = if(x > i) then maxList xs x else maxList xs i

minList :: [Int] -> Int -> Int
minList [] i = i
minList (x : xs) i = if(x < i) then minList xs x else minList xs i

--e)
contains :: Int -> MulTree Int -> Bool
contains x (DatenKnoten a) = if a == x then True else False
contains x (IndexKnoten a b as) = if a > x || x > b then False else (if a == x || b == x then True else contains' x as)
                            
contains' :: Int -> [MulTree Int] -> Bool
contains' x [] = False
contains' x (a:as) = if contains x a then True else contains' x as