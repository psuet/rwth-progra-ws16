data List a = Nil | Cons a (List a) deriving Show

-- Beispiellisten
list :: List Int
list = Cons (-3) (Cons 14 (Cons (-6) (Cons 7 (Cons 1 Nil))))

blist :: List Int
blist = Cons 1 (Cons 1 (Cons 0 (Cons 0 Nil)))

-- Teilaufgabe a)
filterList :: (a -> Bool) -> List a -> List a
filterList f Nil = Nil
-- TODO

-- Teilaufgabe b)
divisibleBy :: Int -> List Int -> List Int
divisibleBy n l = filterList (\x -> x `mod` n == 0) l+