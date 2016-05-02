package me.upalate.trafficker.core.internal.dto;

import java.util.Date;

/**
 * Represents an internal DTO for the asset.
 */
public class IAsset implements java.io.Serializable {
   private long id;
   private long actorId;
   private String timezone;
   private Date dateCreated;

   /**
    * Empty constructor for hibernate.
    */
   public IAsset() {

   }

   public IAsset(long id,
                 long actorId,
                 String timezone,
                 Date dateCreated) {
      this.id = id;
      this.actorId = actorId;
      this.timezone = timezone;
      this.dateCreated = dateCreated;
   }

   public long getActorId() {
      return actorId;
   }

   public String getTimezone() {
      return timezone;
   }

   public Date getDateCreated() {
      return dateCreated;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{")
         .append("actorId: ").append(actorId)
         .append("timezone: ").append(timezone)
         .append("dateCreated: ").append(dateCreated)
         .append("}");

      return sb.toString();
   }
}
