data List a = Nil | Cons a (List a) deriving Show

-- Beispiellisten
list :: List Int
list = Cons (-3) (Cons 14 (Cons (-6) (Cons 7 (Cons 1 Nil))))

blist :: List Int
blist = Cons 1 (Cons 1 (Cons 0 (Cons 0 Nil)))

-- Teilaufgabe a)
filterList :: (a -> Bool) -> List a -> List a
filterList f Nil = Nil
filterList f (Cons a b) = if f a then a `Cons` filterList f b else filterList f b

-- Teilaufgabe b)
divisibleBy :: Int -> List Int -> List Int
divisibleBy n l = filterList (\x -> x `mod` n == 0) l

-- Teilaufgabe c)
foldList :: (a -> b -> b) -> b -> List a -> b
foldList f c (Cons a Nil) = f a c
foldList f c (Cons a b) = f a (foldList f c b)

-- Testfunktion c)
plus :: Int -> Int -> Int
plus x y = x + y

-- Teilaufgabe d)
listMaximum :: List Int -> Int
listMaximum Nil = minBound
listMaximum (Cons a b) = listMaximum' (Cons a b) minBound

listMaximum' :: List Int -> Int -> Int
listMaximum' (Cons a b) max = if a > max then listMaximum' b a else listMaximum' b max
listMaximum' Nil max = max

--Teilaufgabe e)
--zipLists :: (a-> b -> c) -> List a -> List b -> List c

--Teilaufgabe f)
--skalarprodukt :: List Int -> List Int -> Int