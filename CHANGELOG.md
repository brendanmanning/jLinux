*** Changelog starts with version 2.6.1 ***

[2.6.1] 
- Added support for console input to app store apps and external JARs.
- Open and URL command open files and urls (respectively)
    - Available on GUI platforms only
- Worked towards a new error message system (not available to app store apps).
- Added a semi-working angry error messages feature, which makes select error messages a bit more humerous.
    - To activate it, add an empty file named angry.jLinuxBoolean to the .config folder inside jLinux.
- Attempting to run a non-existant program will no longer output 2 similar error messages.
- Squashed other various bugs

[2.7.0]
- Added a basic graphical user interface.
        - Turn it on/off via "gui on" or "gui off"
        - You can also make jLinux start in GUI mode via "gui force"
- Update apps command downloads the latest version(s) of all apps from the Internet.
- Fixed open command.
- Minor bug fixes.
- UI improvements

[2.8] {MAJOR UPDATES}
- Explorer - graphical directory browser. Simply type explorer to graphically list the contents of the current directory.
- TeXT Command - an improved vinf (which will be phased out soon). Supports encryption of text files and multiple lines.
- Error and event logging to log/ folder.
    - Logging runs on it's own thread and writes to a .txt file every 30 seconds.
- Many bug fixes, performance upgrades, and new features
