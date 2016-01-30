--jLinuxInstaller.scpt DOES NOT work fully yet
--Due to the fact that .app files are actually folders, getting them with cURL does not work
--This will be fixed later but isn't a high priority right now
--
-- (c) 2016 Brendan Manning
-- MIT Liscense
display dialog "jLinux Quick Installer..." with title "Install jLinux" buttons {"Install", "Quit"} default button 1
if button returned of result = "Install" then
	display alert "Downloading files from the Internet...If you do not have Internet access, you may be required to install manually" giving up after 2
	tell application "Terminal"
		quit
		delay 5
		activate
		do script "mkdir /Applications/jLinux/"
		delay 1
		do script "curl -f http://thecyberbay.com/cdn/jLinux/latest/latest.jar -o /Applications/jLinux/latest.jar"
		delay 10
		do script "rm /Applications/jLinux/jLinux.jar"
		do script "mv /Applications/jLinux/latest.jar /Applications/jLinux/jLinux.jar"
		--commented code that doesn't work yet
		--delay 1
		--do script "curl -f http://thecyberbay.com/cdn/jLinux/latest/jLinuxLauncher.app -o /Applications/jLinux/jLinuxLauncher.app"
		--delay 10
		--do script "curl -f http://thecyberbay.com/cdn/jLinux/latest/jLinuxUpdater.app -o /Applications/jLinux/jLinuxUpdater.app"
		quit
		delay 5
		activate
		do script "java -jar /Applications/jLinux/jLinux.jar"
		display alert "jLinux Installed!" buttons {"Great", "ALRIGHT!", "Rock on man!"} default button 3
	end tell
else
	display alert "You have aborted jLinux installation. If you would like to return, simply reopen this app!" buttons {"Acknowledged", "Affirmative", "10-4"} default button 2
end if
