#import io
#import argparse

import re, sys, json


# DEBUG MODE VARIABLE
# SET TO TRUE TO ALLOW PRINTING
debugMode = False


def getFilePath(argv):
    return argv[1]


def parseKeyframeData(inputFile):
    if debugMode:
        print "Begginning"
    # Match Keyframe data
    kfPattern = re.compile("^\s(\d+)\s(\d+\.\d+)\s(\d+\.\d+)")
    kfData = open(inputFile, 'r')  # Open the input file
    kfListRough = kfData.readlines()  # Create a list of lines to run through

    kfList = []
    if debugMode:
        print "Done with setup"
    # Loop through all available data, return a list with only relevant info
    for line in kfListRough:
        m = kfPattern.match(line)  
         # Match the kfPattern data
        # m returns none if there was no match, so we can use it to test if
        # there's data here
        if m:
            # The 1st group of the match is the frame number
            frameVal = int(float(m.group(1)))
            # The 2nd group of the match is the x value
            xVal = float(m.group(2))
            # The 3rd group of the match is the y value
            yVal = float(m.group(3))

            # Check to see the length of the kfList.
            if frameVal > len(kfList) and frameVal - len(kfList) != 1:
                tempDiff = frameVal - len(kfList)  # get the difference
                if debugMode: print tempDiff
                # Add a number of empty arrays to the kfList equal to the
                # distance we need to travel
                while tempDiff > 0:
                    kfList.append([0, 0])
                    tempDiff = tempDiff - 1
                # Now the index of the array should match the current keyframe
                # value
            kfList.append([xVal, yVal])

    # Debug mode readuout
    if debugMode:
        tempCount = -1
        for line in range(len(kfList)):
            tempCount = tempCount + 1
            print "Value of line: %r" % line
            print "Keyframe Value : %r" % tempCount
            print "X value: %r" % kfList[line][0]
            print "Y value: %r" % kfList[line][1]
    return kfList

fileInput = getFilePath(sys.argv)
outputData = open("parsedAEData.json", 'w')  # Open to clear out the file
outputData.close()  # Close so we can reopen
outputData = open("parsedAEData.json", 'a')  # Open in append mode
kfList = parseKeyframeData(fileInput) # Parse data into list
json.dump(kfList, outputData)
#for i in range(0, len(kfList))
#   xVal = String(kfList[i][0])
#    yVal = String(kfList[i][1])