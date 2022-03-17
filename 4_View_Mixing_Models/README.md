#### Creating a view mixing two different models

This example does not use *VPDL* to create the viewpoint.

This examples intends to show that we are able to mix different modeling languages using EMF Views to create a single view with linked information between them.

The matching rules are written directly in the src/chain.ecl and it creates rules for the links between the Trace metamodel, the Java metamodel and the UML metamodel to allow users to navigate in a **Log** model and identify the **Classes** in a Java model (extract from a actual Java code) and their correspondent abstract **Component** in the UML. 

The rules at the example are just text comparisons
 

```ecl
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
```


