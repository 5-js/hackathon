<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Auth;
use App\User;
use App\Classroom;
use App\Traits\API;
use App\Http\Resources\Classroom as ClassroomResource;

class ClassroomController extends Controller
{
    use API;

    public function index()
    {
        return $this->respond(['classrooms' => ClassroomResource::collection(Classroom::where('user_id', Auth::id())->latest()->get())] , 'ok');    
    }

    public function store(Request $request)
    {
        if (!Auth::user()->isTeacher())
            return $this->respond(['message' => 'Whoops, You are not authorized to perform this action!'], 'forbidden');
        
        $request->validate(Classroom::rules());

        return $this->respond([
            'classroom' => Classroom::create([
                    'user_id'     => Auth::id(), 
                    'title'       => $request->title, 
                    'description' => $request->description
                ])
            ], 'created');
    }

    public function addStudent($class, Request $request)
    {
        if (!Auth::user()->isTeacher())
            return $this->respond(['message' => 'Whoops, You are not authorized to perform this action!'], 'forbidden');

        if (!$this->studentExists($request->student))
            return $this->respond(['message' => 'Whoops, student does not exists!'], 'not_found');

        if( !$this->classRoomExists($class))
            return $this->respond(['message' => 'Whoops, class does not exists!'], 'not_found');

        Classroom::find($id)->students()->attach($request->student);

        return $this->respond(['message' => 'Student has been successfully added'], 'ok');
    }

    public function deleteStudent($class, Request $request)
    {
        if (!Auth::user()->isTeacher())
            return $this->respond(['message' => 'Whoops, You are not authorized to perform this action!'], 'forbidden');

        if (!$this->studentExists($request->student))
            return $this->respond(['message' => 'Whoops, student does not exists!'], 'not_found');

        if( !$this->classRoomExists($class))
            return $this->respond(['message' => 'Whoops, class does not exists!'], 'not_found');

        Classroom::find($id)->students()->detach($request->student);

        return $this->respond(['message' => 'Student has been successfully removed'], 'ok');
    }
    
    public function show($id)
    {
       if (!$this->classRoomExists($id))
            return $this->respond(['message' => 'Whoops, class does not exists!'], 'not_found');

       return $this->respond(['classroom' => new ClassroomResource(Classroom::find($id))], 'ok');
    }

    public function update(Request $request, $id)
    {
        if (!Auth::user()->isTeacher())
            return $this->respond(['message' => 'Whoops, You are not authorized to perform this action!'], 'forbidden');
        
        if (!$this->classRoomExists($id))
            return $this->respond(['message' => 'Whoops, class does not exists!'], 'not_found');
        
        $request->validate(Classroom::rules());

        Classroom::where('id',$id)->update([
            'user_id'     => Auth::id(), 
            'title'       => $request->title, 
            'description' => $request->description
        ]);
    }

    public function destroy($id)
    {
        if (!Auth::user()->isTeacher())
            return $this->respond(['message' => 'Whoops, You are not authorized to perform this action!'], 'forbidden');
        
        if (!$this->classRoomExists($id))
            return $this->respond(['message' => 'Whoops, class does not exists!'], 'not_found');

        Classroom::where('id',$id)->destroy();
    }

    public function classRoomExists($id)
    {
        return Classroom::find($id)? true : false;
    }

    public function studentExists($id)
    {
        return User::find($id)? true : false;
    }
}
