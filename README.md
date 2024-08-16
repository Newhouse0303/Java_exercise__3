/*
The `GeometricsCalculatorApp` is used to calculate different things about geometrical shapes. 
It has a console user interface to ask what kind of shape the user would like to handle 
and what are the coordinates for the calculations. After processing the input data the program 
prints the results onto the console. The shapes are predifined and so far are 
circle, quadrilateral and triangle are supported.

About the structure: 

The main functionalities are divided in classes 1) `GeometricsCalculatorApp` 2) `Calculator` 
3) `ShapeManager` and 4) abstract class `Shape` and its subclasses. The first one could be a lot cleaner, 
but it is basically responsible for passing the user input (type of shape) to `ShapeManager` which 
returns a list of the coordinates for the required shapes. 

The initial code was designed to work with two objects but this version can be modified by 
changing the `N_OBJECTS` in the `GeometricsCalculatorApp` class. In the future the value could also be modified by the user. 

The class `Shape` offers the basic attributes shared by all "subshapes" ( number of coordinate Points, list of the Points )
and empty frames for calculation methods which are overriden in the subclassess accordingly. The actual math is omitted
here but the class structure would make it easy to delegate the task (with appropriate definitions) to someone else.

The super class `Shape` is useful in initialising the list of whatever-the-shape-might-be in line 59, as the parameter shape 
is defined by the user and the `ShapeManager` returns a list of required "subshapes" : 
`Shape[] objects = ShapeManager.createObjects(shape, N_OBJECTS)`;

When the `Calculator` class processes the `Shape[] objects` list further, the newly created shapes e.g. Triangles will directly 
call the methods in the Triangle class thus using the correct method (see line 88 or below). This will make the code reusable regardless of what type of shapes added to the mix.
```
static double getSumOfAreas(Shape[] objects) {    // here each object is of the chosen subtype
    return Arrays.stream(objects)          
                 .mapToDouble(Shape::calculateArea) 
                 .sum();                   
}

```
As for general modularity and reusability, the predefined but dynamic values (number of shapes, supported shapes, supported calculations) would live in a different `configApp` making the userInterface totally generic and easy to replace with a graphic UI. 
Then adding new shapes would only entail updating `configApp`, creating a subclass and appropriate methods. The calculations could also
be distributed to different interfaces to add flexibility for prospective, more complex stuff. This way the Shape subclasses would mainly hold data, implement suitable interfaces and override the methods accordingly.

I would also pay more attention to the separation of concerns for example on line 47 in the `Calculator` prints the result when that is 
clearly the business of the user interface. Also the `record Point(int x, int y) {}` is alone on the top level where it should be declared within a class, maybe in `userInterface`where it is used. 
