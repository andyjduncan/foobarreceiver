# Thing service

Create a service to receive Things over the network.  Things come in two
varieties: Foos and Bars.  Both Foos and Bars will be posted to this
service and should be stored for later retrieval.

## Thing formats

All Things have an id.  Different varieties of Thing have their own
specific ids.

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