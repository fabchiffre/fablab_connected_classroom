#!/usr/bin/env python

import socket

etudiants = {'04ec67ea4b2880': "Fabien", '04d867ea4b2880': "Christophe", '040167ea4b2881' : "Daniel"}


TCP_IP = '127.0.0.1'
TCP_PORT = 10000
BUFFER_SIZE = 1024  # Normally 1024, but we want fast response

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_address = ('169.254.0.1', 10001)
s.bind(server_address)
s.listen(1)
conn, addr = s.accept()
print 'Connection address:', addr
while 1:
    data = conn.recv(BUFFER_SIZE)
    if not data: break
    print etudiants[data] if data in etudiants.keys() else "Etudiant inconnu"
conn.close()
