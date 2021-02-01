# FileUtilsPlus - An android library 

## Story of creation 
While creating a app which used to do many file operatation like copying,renaming,moving,deleting etc. I have to write many lines of code for performing a simple operatation.
so my activity get overloaded by the methods of doing such things. I wasted 2 days for just handling errors/ excaption & it took about 6 days to find the correct way of doing it. So I thought to create a utility class for me and my friends as well, to save their time. This incident forsed me to wrote this library.


### Methods :-

- **manageStoragePermission(Activity activity)**    - for requesting storage permission
- **copyFromAssets(Activity activity ,String filename, String path)** --  for copying a perticulat file from assets folder
- **copy(String filepath, String to)**  -- for copying a file from one path to another
- **copyAllAssets(Activity activity ,String path)** -- for copying all files from assets folder
- *many more...*
