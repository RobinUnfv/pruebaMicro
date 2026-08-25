db = db.getSiblingDB('venta_db');

// -----------------------------
// COLLECTION: compra
// -----------------------------
db.createCollection('compras');

db.compras.insertMany([
  {
    _id: 'cod01',
    id_ciente: '11111111-1111-1111-1111-111111111111',
    fecha: new Date('2026-08-14T00:00:00Z'),
    compra: 'Polo Negro talla M Jorge Chavez'
  },
  {
    _id: 'cod02',
    id_ciente: '22222222-2222-2222-2222-222222222222',
    fecha: new Date('2026-08-14T00:00:00Z'),
    compra: 'Camisa Blanca talla L'
  }
]);
