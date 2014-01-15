import os
import sys
import subprocess

CONFIG_FILE_NAME = "config.json"
ROSTER_FILE_NAME = "names.txt"
OUTPUT_DIR = 'C:\\Users\\ben\\Desktop\\doneDoorDecs\\'

#Load config file
try:
   with open(CONFIG_FILE_NAME):
       process()
except IOError:
   print 'WARNING - config file not found.  Running overlay with default parameters.'

#Load names from roster
with open(ROSTER_FILE_NAME) as f:
    names = [line.strip("\n") for line in f.readlines()]


for dirPath, dirNames, fileNames in os.walk('.'):
	for fileName in fileNames:
		
		ext = fileName[-4:]
		nameIndex = fileName[:-4]
		
		if (ext == '.jpg'):
			
			if not nameIndex.isdigit():
				print 'Unable to convert the following image into index: ' + fileName + ' If this image was not meant to be made have a text overlay, you should disregard this message.'

			else:
				#corect for incorrect human indexing
				nameIndex = int(nameIndex) - 1
				try:
					name = names[int(nameIndex)]
					fullFileName = fileName
					if not dirPath == '.':
						fullFileName = dirPath + fileName

					command = 'convert ' + fullFileName + ' -font Arial -pointsize 180 -draw "gravity south fill black  text 0,12 ' + name + ' fill white  text 1,11 \'' + name + '\'" \'' + OUTPUT_DIR + fileName + '\''
					print command
					subprocess.call(command)
				except IndexError:
					print 'Unable to access the ' + str(nameIndex + 1) + '\'th name in the roster file.  Perhaps an image is named a number that is less than 1, or larger than the number of residents.'
					sys.exit()
				except CalledProcessError:
					print 'Unable to run command: ' + command