package com.example.bruger.feetclinic.BLL.BE;

import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

/**
 * Created by Stepanenko on 01/05/2016.
 */
@Table
public class TreatmentORM extends Treatment {
    private Long id;
    private boolean deleted;
    private boolean modified;
    private boolean created;
    public TreatmentORM() {
    }

    public TreatmentORM(Treatment treatment){
        super(treatment);
    }
    public void update(Treatment treatment){
        this.name = treatment.getName();
        this._id = treatment.get_Id();
        this.description = treatment.getDescription();
        this.imageUrl = treatment.getImageUrl();
        this.price = treatment.getPrice();
        this.duration = treatment.getDuration();
        this.type = treatment.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    @Override
    public String get_Id() {
        if (super.get_Id() == null){
            return String.valueOf(getId());
        }
        return super.get_Id();
    }

    public boolean isSynchronized(){
        return !(modified && deleted && created);
    }
}
