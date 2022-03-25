rule bookCode
match s : publication!Publication
with  t : book!Book
{
  compare
  {
    return s.Code = t.ISBN.split(".")[0];
  }
}

rule publicationISBN
match s : book!Book
with  t : publication!Publication
{
  compare
  {
    return t.ISBN.split(".")[0] = s.Code;
  }
}