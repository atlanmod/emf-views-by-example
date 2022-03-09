#### Using a view to copy all information from an actual model

Using [VPDL](https://www.atlanmod.org/emfviews/manual/user.html#vpdl) to create a view that just copies all information from a model. This is not useful at all, but it's good to understand that the view does not COPY the model source, but creates just a virtual link between the model and the view, so, when the model is edited, the view is automatically updated.

*After changes in the VPDL file, it's necessary to save it so Eclipse will generate the src-gen folder with viewpoint files.*