# Foo Bar service

Create a service to receive foos and bars over the network.  Foos and
bars will be posted to this service and should be stored for later
retrieval.

## Formats

### Foo format

foo:{id}:{fooid}

id: 4 digits

fooid: 4 hex digits

### Bar format

bar:{id}:{barid}:{bazid}

id: 4 digits

barid: 3 hex digits

bazid: 3 hex digits

## API

The API will be HTTP based with the following endpoint:

POST /receive?thing={foo | bar}