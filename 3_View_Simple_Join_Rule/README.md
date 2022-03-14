#### Creating a view using a very simple rule

This is also based on the EMF Views [VPDL tutorial](https://www.atlanmod.org/emfviews/manual/user.html#writing-a-vpdl-file) and is good to demonstrate the creation of simple rules using the [Epsilon Comparison Language (ECL)](https://www.eclipse.org/epsilon/doc/ecl/).

The rule in the where clause is responsible for the combination between the Book and the Publication instances comparing their titles and filtering just the first Chapter of each instance. 

```
s.title = t.title for bookPublisher
```

The features of each model are filtered out based on the declarations in the SELECT statement.

```
 publication.Publication.publisher,
 book.Chapter.title,
 book.Book.*,
 book.Book join publication.Publication  as bookPublisher,
```

*After changes in the VPDL file, it's necessary to save it so Eclipse will generate the src-gen folder with viewpoint files.*