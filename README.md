# FileUtilsPlus - An android library 

## Story of creation 
While creating a app which used to do many file operatation like copying,renaming,moving,deleting etc. I have to write many lines of code for performing a simple operatation.
so my activity get overloaded by the methods of doing such things. I wasted 2 days for just handling errors/ excaption & it took about 6 days to find the correct way of doing it. So I thought to create a utility class for me and my friends as well, to save their time. This incident forsed me to wrote this library.


### Implementation
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  In your app level :-
  ```
  dependencies {
	        implementation 'com.github.ErrorxCode:FileUtilsPlus:1.0'
	}
  ```


#### Methods :-

- **manageStoragePermission(Activity activity)**    - for requesting storage permission
- **copyFromAssets(Activity activity ,String filename, String path)** --  for copying a perticulat file from assets folder
- **copy(String filepath, String to)**  -- for copying a file from one path to another
- **copyAllAssets(Activity activity ,String path)** -- for copying all files from assets folder
- **moveFile(String filename, String src, String dest)** -- for moing a file to another directory
- **rename(String srcPath, String renameTo)** -- for renaming a file
- **delete(Activity activity,String filepath)** -- for deleting a file or directory
- **UnZip(String source, String path)** -- for unziping a zip file with no password
- **writeFile(String filepath, String data,boolean overwrite)** -- for writing a existing file , usually a txt file.
- **readFile(String filepath)** -- for reading contents of a file , usually txt
- **eraseFile(String filepath,String what)** -- for erasing text from a txt file
- **oneTimeEvent(Activity activity,OneTimeEventInterface eventInterface)** -- for doing something for only once.
- **oneTimeDialog(Activity activity ,OneTimeDialogInterface dialogInterface)** -- for showing alertdialog for only one time.


> Thanks for using my library.
