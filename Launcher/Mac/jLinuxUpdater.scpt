display dialog "Opening jLinux Updater..." with title "jLinux Updater" buttons {"Ok"} default button 1
display alert "Updating jLinux will close all active Terminal sessions and overwrite your current jLinux executable. Is this OK?" buttons {"Yes", "No"} default button 1
if button returned of result = "Yes" then
	display alert "Updating..." giving up after 2
	tell application "Terminal"
		quit
		delay 5
		activate
		do script "curl -f http://thecyberbay.com/cdn/jLinux/latest/latest.jar -o /Applications/jLinux/latest.jar"
		delay 10
		do script "rm /Applications/jLinux/jLinux.jar"
		do script "mv /Applications/jLinux/latest.jar /Applications/jLinux/jLinux.jar"
		quit
		delay 1
	end tell
	display alert "Updated! Relaunching jLinux!" giving up after 3
	tell application "Terminal"
		do script "java -jar /Applications/jLinux/jLinux.jar"
	end tell
else
	display alert "jLinux has NOT been updated! Re-run this program at any time to update to the latest jLinux version!" giving up after 6
end if
