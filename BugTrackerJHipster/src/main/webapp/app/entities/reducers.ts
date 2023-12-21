import project from 'app/entities/project/project.reducer';
import label from 'app/entities/label/label.reducer';
import ticket from 'app/entities/ticket/ticket.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  project,
  label,
  ticket,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
