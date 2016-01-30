set p to text returned of (display dialog "Enter path to jLinux executable" default answer "/Applications/jLinux/jLinux.jar" buttons {"Ok"} default button 1 with title "Start jLinux")
tell application "Terminal"
	activate
	do script "java -jar " & p
end tell

-- (c) 2016 Brendan Manning
