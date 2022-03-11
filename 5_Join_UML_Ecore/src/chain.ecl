rule javaClass
match l : trace!Log
with  c : java!ClassDeclaration
{
  compare
  {
    return l.source.split("\\.")[0] = c.name;
  }
}

rule component
match p : java!Package
with  c : uml!Component
{
  compare
  {
    return p.name = c.name.toLowerCase();
  }
}