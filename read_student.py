import nfc
import time
import socket

def connected(tag):
    uid= tag.__str__().split(" ")[3][4:]
    s.send(uid)
    print uid

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_address = ('169.254.0.1', 10001)
s.connect(server_address)
clf = nfc.ContactlessFrontend('usb')
while 1:
    clf.connect(rdwr={'on-connect' : connected})
    time.sleep(1)
s.close()

