<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class User extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'name'          => $this->name, 
            'email'         => $this->email, 
            'mobile'        => $this->mobile, 
            'avatar'        => $this->avatar, 
            'role'          => $this->role,
            'date'          => date("F d, Y", strtotime($this->created_at)), 
            'time'          => date("h:i:s A", strtotime($this->created_at)), 
            'datetime'      => date("F d, Y - h:i:s A", strtotime($this->created_at)), 
            'human_time'    => $this->created_at->diffForHumans(),
        ];
    }
}
