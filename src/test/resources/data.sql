INSERT INTO company
(id, name)
VALUES
  (NEXTVAL('company_id_seq'), 'Test_name'),
  (NEXTVAL('company_id_seq'), 'Test_name2'),
  (NEXTVAL('company_id_seq'), 'Test_name3');

INSERT INTO document
 (id, name, initiator_id, initiation_date_time, sender_id, electronic_signature_sender, recipient_id, electronic_signature_recipient, info)
VALUES
  (NEXTVAL('document_id_seq'), 'Document_Test', 1,'	2019-08-01 13:58:48.956'::TIMESTAMP, 1, false, 2, false, '');