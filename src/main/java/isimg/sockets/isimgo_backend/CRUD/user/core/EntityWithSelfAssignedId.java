package isimg.sockets.isimgo_backend.CRUD.user.core;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

public abstract class EntityWithSelfAssignedId <ID> implements Persistable<ID> {

    @Transient
    private boolean persisted;

    @Override
    public boolean isNew() { return !persisted; }

    @PostPersist
    @PostLoad
    private void updatePersisted() {
        persisted = true;
    }
}
