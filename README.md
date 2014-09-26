cellsociety
===========
Empty repository for CellSociety project
Group 16 - Cell Society: 
Abhishek Balakrishnan
Kevin Li
Rahul Harikrishnan

In order to run the simulation, you should run CellWorld.java. This is the class that starts the animation
timeline in the CellViewer.  One can load different XML files by clicking on the ... button in the File Explorer
bar on the top of the GUI.  We support the 4 originally specified models, namely, Game Of Life, Segregation,
Shark(Predator) & Fish(Prey), and the Fire Simulation.  In addition, we have support for SugarScape, which is a part of 
the second set of implementation specifications.  You can dynamically interact with the simulations by 
clicking on the cells which toggles their state and you can view the simulation with these modifications.  We have implemented error checking for inputs that do not make sense
such as negative grid size, no simulation mode specified, cells out of bounds, and if the threshold parameter is below 
zero. cellShape and gridBoundaryType.  We assign default states when both gridShapeType and the gridBoundaryType are unspecified.
The button functions should be self-explanatory in nature and responsive to user interaction.
